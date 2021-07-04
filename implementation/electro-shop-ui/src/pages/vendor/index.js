import React, { useEffect, useState } from "react";

import Loader from "../../components/loader";

import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import DeleteIcon from '@material-ui/icons/Delete';
import TextField from '@material-ui/core/TextField';
import { endpoints } from "../../api";
import { SettingsInputSvideoRounded } from "@material-ui/icons";

const useStyles = makeStyles({
    root: {
        maxWidth: 500,
    },
});

const Vendor = props => {
    // Material styles
    const classes = useStyles();

    const [isLoading, setIsLoading] = useState(true);
    const [vendor, setVendor] = useState(null);
    const [newPrice, setNewPrice] = useState(null)
    // const [inventory, setInventory] = useState([1, 2, 3, 4, 5]);
    const [inventory, setInventory] = useState(null);
    const [profit, setProfits] = useState(0);
    const [toRemove, setToRemove] = useState(null);

    const fetchVendor = async () => {
        const response = await fetch(endpoints.vendor.vendor);
        const data = await response.json();
        setVendor(data);
        console.log(data)
        setIsLoading(false);
    }

    const postPrice = async () => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "vendor": props.user.attributes.principal.uuid, "price": newPrice.price })
        }

        try {
            const response = await fetch(`http://localhost:4006/api/v1/products/${newPrice.product}/price`, requestOptions);
            const data = await response.json();
            console.log("response to PUT", data)

            setIsLoading(false);
        } catch (error) {
            console.log('error :>> ', error);
        }
    }

    useEffect(() => {
        if (newPrice) {
            postPrice()
        }
    }, [newPrice])

    useEffect(() => {
        toRemove && putRemoveFromInventory();
    }, [toRemove])

    useEffect(async () => {
        setVendor(props.user);
        setIsLoading(false);
        const data = await fetchInventory();
        const profit = await fetchProfits();
    }, [])

    useEffect(async () => {
        console.log("Reload")
        fetchInventory();
    }, [isLoading])

    const fetchInventory = async () => {
        const res = await fetch(`http://localhost:8089/api/v1/vendor/${props.user.attributes.principal.uuid}/inventory`);
        const data = await res.json();
        console.log(data)
        setInventory(data);
    }

    const fetchProfits = async () => {
        const res = await fetch(`http://localhost:8089/api/v1/api/v1/sellers/${props.user.attributes.principal.uuid}/profits`);
        const data = await res.json();
        console.log("profits", data);
        setProfits(data)
    }

    const putRemoveFromInventory = async () => {
        let options = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "inventoryUuid": inventory.inventoryUuid,
                "vendor": props.user.attributes.principal.uuid,
                "productIdListToRemove": [toRemove],
                "productsToAdd": []
            })
        };
        console.log(options)
        const res = await fetch(`http://localhost:4006/api/v1/vendor/${props.user.attributes.principal.uuid}/inventory`,
            options)
        const data = await res.json();
        fetchInventory()
        // setInventory(data)
        console.log("response after deleting >>", data)
        // console.log("refetched", data)
        // setInventory(data);
    }

    const handleRemoveFromInventory = (e, data) => {
        setToRemove(data);
        console.log('removing product from inventory ... :>> ', data);
    }

    const handleAdjustPrice = (e) => {
        e.preventDefault();
        let value = e.target.querySelector('input').value;
        let product = e.target.querySelector('input').id;
        setIsLoading(true);
        setNewPrice({ "product": product, "price": value });
    }

    // alcoholPercent: 5.3
    // brand: "Imperial Stout"
    // currency: "EUR"
    // description: "A grizzly beer accidentally buys an expensive drink for a Pilsner Urquell. A precise Busch reads a magazine, or a mitochondrial sake bomb avoids contact with the Amarillo Pale Ale. Marzipan bonbon cake chocolate sugar plum lollipop gummi bears dragée jelly beans."
    // img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRyPo1-ApcdpROupLVEtkInKh8hC5I2FFhdHbdynOEKtMZATI-ANmINsg6&usqp=CAc"
    // name: "Mock Product B"
    // price: 262.38
    // rating: 3.6
    // uuid: "4c68e51d-04d5-4c3d-b943-79e7fcaea92f

    return (
        <>
            {isLoading && !inventory && <Loader />}
            {!isLoading && inventory && props.user && <div>
                <Card className={classes.root}>
                    <CardActionArea>
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="h2">
                                <strong>Vendor</strong>
                            : {props.user.name}
                            </Typography>
                            <Typography variant="body2" color="textPrimary" component="p">
                                Inventory value:{" "}<strong>{inventory.inventoryValue}{" EUR"}</strong>
                            </Typography>
                            <Typography variant="body2" color="textPrimary" component="p">
                                {/* TODO */}
                                Profits:{" "}<strong>{profit}</strong>
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                </Card>

                <h2>Inventory</h2>
                {inventory.products.map((el, index) => (<Card key={index} className="inventory-product">
                    <div>
                        <strong>{el.product.name + " (" + el.product.brand + ") "}</strong>
                        <Button variant="outlined" id={el.product.uuid} color="secondary" onClick={(e, data) => handleRemoveFromInventory(e, el.product.uuid)}>
                            <DeleteIcon />
                        </Button>
                    </div>
                    <ul>
                        <li>Stock amount: <strong>{el.amount}</strong></li>
                        <li>Price: <strong>{el.product.price} EUR</strong></li>
                        <li>Rating: <strong>⭐: <strong>{el.product.rating} / 5</strong></strong></li>
                    </ul>
                    <form onSubmit={handleAdjustPrice}>
                        <TextField id={el.product.uuid} label="Adjust Price" />
                        <Button variant="contained" color="primary" type="submit">Adjust Price</Button>
                    </form>
                </Card>))}
            </div>
            }
        </>
    )
}

export default Vendor