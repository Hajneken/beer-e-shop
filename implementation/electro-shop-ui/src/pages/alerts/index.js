import React, { useEffect, useState } from "react";
import UnsubscribeIcon from '@material-ui/icons/Unsubscribe';
import DeleteIcon from '@material-ui/icons/Delete';

import Button from '@material-ui/core/Button';


const Alerts = props => {

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
            <h1>Price Alert Subscriptions</h1>
            {
                (props.alerts.length === 0) && <strong>No subscriptions</strong>
            }
            {(props.alerts.length > 0) && <>
                <ul>
                    {props.alerts.map((item, index) => (
                        <li key={index}>
                            <Button variant="outlined" color="secondary" onClick={e => props.handleRemovePriceAlert(e, item.data)}>
                                <UnsubscribeIcon />
                            </Button> <strong>{item.data.name}</strong>
                            {" " + "(" + item.data.brand + ") "}
                            <strong>{item.data.price}{" " + item.data.currency}</strong>
                        </li>))}
                </ul>
            </>}
        </div>
    )
}

export default Alerts;
