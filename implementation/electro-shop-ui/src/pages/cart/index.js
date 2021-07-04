import React from "react";


import DeleteIcon from '@material-ui/icons/Delete';
import CreditCardIcon from '@material-ui/icons/CreditCard';

import Button from '@material-ui/core/Button';
import { Link } from "react-router-dom";


const Cart = props => {

    //  alcoholPercent: 5.3
    // brand: "Imperial Stout"
    // currency: "EUR"
    // description: "A grizzly beer accidentally buys an expensive drink for a Pilsner Urquell. A precise Busch reads a magazine, or a mitochondrial sake bomb avoids contact with the Amarillo Pale Ale. Marzipan bonbon cake chocolate sugar plum lollipop gummi bears dragée jelly beans."
    // img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRyPo1-ApcdpROupLVEtkInKh8hC5I2FFhdHbdynOEKtMZATI-ANmINsg6&usqp=CAc"
    // name: "Mock Product B"
    // price: 262.38
    // rating: 3.6
    // uuid: "4c68e51d-04d5-4c3d-b943-79e7fcaea92f

    return (
        <div className="cart">
            <h1>Cart</h1>
            {
                (props.cart.length > 0) && <>
                    <Link to="checkout" className="no-underline">
                        <Button variant="contained" size="large" color="primary">
                            <CreditCardIcon /> <strong>Proceed to Checkout</strong>
                        </Button>
                    </Link>
                    <ul>
                        {props.cart.map((item, index) => (
                            <li key={index}>
                                <Button variant="outlined" color="secondary" onClick={e => props.handleRemoveFromCart(e, item.data)}>
                                    <DeleteIcon />
                                </Button> <strong>{item.data.name}</strong>
                                {" " + "(" + item.data.brand + ") "}
                                <strong>{item.data.price}{" " + item.data.currency}</strong>
                            </li>))}
                    </ul>
                </>

            }
            {
                (props.cart.length === 0) && <strong>Your cart is empty 🕸</strong>
            }
            {/* <DataGrid rows={rows} autoHeight columns={columns} pageSize={5} checkboxSelection onSelectionModelChange={handleSelect} /> */}
        </div>
    )
}

export default Cart;
