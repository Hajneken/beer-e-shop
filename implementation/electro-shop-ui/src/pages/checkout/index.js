import React, { useEffect, useState } from "react";
import Button from '@material-ui/core/Button';

const Checkout = props => {

    const [placed, setPlaced] = useState(false)

    useEffect(() => {

    }, [])

    const handleCheckout = (e) => {
        setPlaced(true)
        props.handleCheckout(e)
        console.log("order Placed", e)
    }


    return (
        <div>
            <h1>Cart Checkout</h1>

            {
                (props.cart.length > 0) && <>
                    <div>
                        <ol>
                            {props.cart.map((item, index) => (
                                <li key={index}>
                                    <strong>{item.data.name}</strong>
                                    {" " + "(" + item.data.brand + ") "}
                                    <strong>{item.data.price}{" " + item.data.currency}</strong>
                                </li>))}
                        </ol>

                        <div>
                            <ul>
                                <li>Number of items: <strong>{props.cart.length}</strong></li>
                                {/* TODO fetch */}
                                <li>Total amount: <strong>203.32</strong> EUR</li>
                            </ul>
                        </div>
                    </div>
                    <div className="center"><Button variant="contained" color="primary" onClick={handleCheckout}>Place Order</Button>
                    </div>
                </>

            }
            {
                (props.cart.length === 0) && !placed && <strong>Nothing to Checkout</strong>
            }
            { (props.cart.length === 0) && placed && <>
                <div>
                    <h2 style={{ "textAlign": "center" }}>Your order was successfully placed!</h2>
                    <p>Your order is being processed.</p>
                    <p>Thank you!</p>
                </div>
            </>}
        </div>

    )
}

export default Checkout;