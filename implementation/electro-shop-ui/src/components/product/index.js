import React, { useEffect, useState } from "react";

import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import Typography from '@material-ui/core/Typography';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import NotificationsActiveIcon from '@material-ui/icons/NotificationsActive';
import { Link } from "react-router-dom";


const useStyles = makeStyles({
    root: {
        maxWidth: 350,
    },
});

const Product = props => {

    const [product, setProduct] = useState(null);
    const [priceAlert, setPriceAlert] = useState("");
    const classes = useStyles();

    const handleAddToCart = (e) => {
        // console.log(props.handleAddToCart)
        props.handleAddToCart(e, product)
    }

    const handleAddAlert = (e) => {
        // console.log('e :>> ', e);
        const payload = {
            product: product.data.uuid,
            price: priceAlert
        }

        props.handleAddAlert(e, payload)
    }

    const handlePriceAlertInput = event => {
        //console.log({ val: event.target.value });
        setPriceAlert(event.target.value);
    };


    useEffect(() => {
        setProduct(props);
    }, [props])

    return (
        <div>
            {product && <Card style={{ "margin": "0.4rem" }} className={classes.root}>
                <Link to={`/products/${props.data.uuid}`} className="no-underline">
                    <CardActionArea>
                        <CardMedia
                            component="img"
                            alt={props.data.name + " " + props.data.brand}
                            height="140"
                            image={props.data.img}
                            title={props.data.name + " " + props.data.brand}
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="h2">
                                {props.data.name}
                            </Typography>
                            <Typography variant="body2" color="textSecondary" component="p">
                                {props.data.description}
                            </Typography>
                            <Typography variant="body1" color="textPrimary" component="p">
                                ⭐: <strong>{props.data.rating} / 5</strong>
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                </Link>
                <CardActions>
                    <Button variant="contained" size="large" color="primary" onClick={handleAddToCart}>
                        <AddShoppingCartIcon />
                    </Button>
                    <br />
                    <Input type="number" onChange={handlePriceAlertInput} value={priceAlert} label="Notify for price:" />;
                    <Button variant="contained" size="large" color="secondary" onClick={handleAddAlert}>
                        Price <NotificationsActiveIcon />
                    </Button>
                    <Typography variant="body1" color="textPrimary" component="p">
                        Price: <strong>{props.data.price}</strong> {props.data.currency}
                    </Typography>
                </CardActions>
            </Card>
            }
        </div>
    )
}

export default Product;