import './App.css';

import React, {useEffect} from 'react';
import {useState} from "react";

// Pages
import Products from "./pages/products";
import {BrowserRouter, Link, Route, Switch} from 'react-router-dom';
import ProductDetail from "./pages/ProductDetail";
import Vendor from "./pages/vendor"
import Cart from "./pages/cart";
import Checkout from "./pages/checkout";
import Alerts from "./pages/alerts";
import NoMatch from "./pages/404"
import History from "./pages/history"

// Material
import Badge from '@material-ui/core/Badge';
import {withStyles} from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import NotificationsNoneIcon from '@material-ui/icons/NotificationsNone';
import {useCookies} from 'react-cookie';
import {Button} from '@material-ui/core';
import {api, endpoints, registerInterceptor} from './api';
import {ErrorOutlineRounded} from '@material-ui/icons';

const StyledBadge = withStyles((theme) => ({
    badge: {
        right: -3,
        top: 13,
        border: `2px solid ${theme.palette.background.paper}`,
        padding: '0 4px',
    },
}))(Badge);

// registerInterceptor();

const App = () => {
    const [cart, setCart] = useState([]);
    const [alerts, setAlerts] = useState([]);
    // const [user, setUser] = useState(null);
    const [user, setUser] = useState({type: "vendor", username: "Example Name", uuid: "2345-12345-1355-15523"});
    const [cookies, setCookie, removeCookie] = useCookies(null);

    const [url] = useState(endpoints);

    const handleAddToCart = (e, data) => {
        console.log("Adding product to the cart... :>>", data)
        // TODO 
        //send the cart to Back End only when going to checkout
        setCart([...cart, data]);
    }

    const handleAddAlert = async (e, data) => {
        console.log("Adding alert to price alerts... :>>", data)
        try {
            let newNotification = await api.addProductMarking({
                emailAddress: user.username,
                user: user.uuid,
                product: data.product,
                price: data.price
            });

            setAlerts([await api.getNotifications(user.uuid)]);
        } catch (err) {
            console.error(err);
        }
    }

    const handleRemoveFromCart = (e, data) => {
        console.log('Removing product from the cart :>> ', data);
        // TODO what to 
        let index = cart.findIndex(el => el.data.uuid === data.uuid);
        setCart(cart.filter((el, i) => i !== index));
    }

    const handleRemovePriceAlert = async (e, data) => {
        console.log('Removing Price alert ... :>> ', data);
        setAlerts([await api.getNotifications(user.uuid)]);
    }

    const handleCheckout = (e) => {
        console.log('Checked out :>> ', e);
        // TODO post data 
        setCart([]);
    }

    const handleLogOut = (e) => {
        removeCookie("JSESSIONID");
        window.location.href = 'http://localhost:8089/logout'
        console.log('Logging Out :>> ', e);
    }

    useEffect(() => {
        handleLogin();
        console.log("cookies", cookies);
    }, [])

    const handleLogin = async () => {
        let fetchedUser = await api.getCurrentUser();
        console.log("setting user ", fetchedUser);
        setUser(fetchedUser);
        setCookie("JSESSIONID");
    }

    useEffect(() => {
        console.log("cart effect ", cart)

        // /api/v1/api/v1/users/{userId}/cart/product/{productId}

        // TODO where to send 
    }, [cart])

    useEffect(() => {
        console.log("price effect ", cart)
        // TODO where to send 
    }, [alerts])

    return (
        <BrowserRouter>
            <h1 style={{"textTransform": "uppercase"}}>Beer shop 🍻</h1>
            
            <div className="top-menu">
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        {
                            (user.attributes && user.attributes.authorities.length >= 2) && <>
                                <li>
                                    <Link to="/vendor">Vendor Management</Link>
                                </li>
                            </>
                        }
                        <li>
                            <Link to="/history">Transaction history</Link>
                        </li>
                    </ul>
                </nav>
                <Link to="/cart">
                    <IconButton aria-label="cart">
                        <StyledBadge badgeContent={cart.length} color="secondary">
                            <ShoppingCartIcon size="large" color="primary"/>
                        </StyledBadge>
                    </IconButton>
                </Link>
                <Link to="/alerts">
                    <IconButton aria-label="list of price alerts">
                        <StyledBadge badgeContent={alerts.length} color="secondary">
                            <NotificationsNoneIcon color="secondary"/>
                        </StyledBadge>
                    </IconButton>
                </Link>

                <Button color="secondary" onClick={handleLogOut}>
                    <strong>Log Out</strong></Button>
            </div>
            <div className="layout">
                <Switch>
                    <Route path="/vendor">
                        {(user.attributes && user.attributes.authorities.length >= 2) ? <Vendor
                            user={user}
                            URL={url.vendor.vendor}/> : <NoMatch/>}
                    </Route>
                    <Route path="/products/:productID">
                        <ProductDetail
                            handleAddToCart={handleAddToCart}
                            handleAddAlert={handleAddAlert}/>
                    </Route>
                    <Route path="/cart">
                        <Cart
                            cart={cart}
                            handleRemoveFromCart={handleRemoveFromCart}
                            URL={url.user.cart}/>
                    </Route>
                    {/* <Route path="/login">
                        <Login
                            URL={loginURL} />
                    </Route> */}
                    <Route path="/checkout">
                        <Checkout
                            URL={url.transaction.checkout} cart={cart} handleCheckout={handleCheckout}/>
                    </Route>
                    <Route path="/history">
                        <History
                            URL={url.transaction.history}/>
                    </Route>
                    <Route path="/alerts">
                        <Alerts
                            alerts={alerts}
                            handleRemovePriceAlert={handleRemovePriceAlert}
                            URL={url.notification.productMarking}/>
                    </Route>
                    <Route exact path="/">
                        <Products
                            handleAddToCart={handleAddToCart}
                            handleAddAlert={handleAddAlert}
                            URL={url.product.products}/>
                    </Route>
                    <Route path="*">
                        <NoMatch/>
                    </Route>
                </Switch>
            </div>
        </BrowserRouter>
    );
}

export default App;
