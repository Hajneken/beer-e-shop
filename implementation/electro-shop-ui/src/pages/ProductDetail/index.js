import React, { useEffect, useState } from "react";

import Product from "../../components/product";
import Loader from "../../components/loader";
import { useParams } from "react-router-dom"

import Rating from '@material-ui/lab/Rating';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';

import { makeStyles } from '@material-ui/core/styles';
import { Button } from "@material-ui/core";
import { api, endpoints } from "../../api";

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
    },
});

const ProductDetail = props => {
    const { productID } = useParams()

    const [product, setProduct] = useState(null);
    const [value, setValue] = useState(0);
    const [ratingChanged, setRatingChanged] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        if (ratingChanged) {
            postRating()
        } else {
            fetchProduct();
        }
    }, [ratingChanged])

    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "uuid": productID, "rating": value })
    };

    const fetchProduct = async () => {
        try {
            const res = await fetch(`http://localhost:4006/api/v1/products/${productID}`)
            const data = await res.json();
            // const data = await api.getProducts();
            console.log('data :>> ', data);
            setProduct(data);
            setValue(data.rating);
            setIsLoading(false);

        } catch (error) {
            console.log(error)
        }
    }

    const handleSubmitRating = (e) => {
        // console.log(`Rating: ${value} submitted`, e);
        // console.log(product)
        setIsLoading(true);
        setRatingChanged(true);
    }

    const postRating = async () => {
        const options = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "rating": value })
        };

        try {
            // const response = await fetch(endpoints.product.products + productID, requestOptions);
            const res = await fetch(`http://localhost:4006/api/v1/products/${productID}/rating`, options)
            // should return product data
            const data = await res.json();
            setProduct(data)
            setValue(data.rating);
            setRatingChanged(true)
            setIsLoading(false)

        } catch (error) {
            console.log(error)
            // Fetch product if error occurs
            fetchProduct();
        }
    }

    return (
        <>
            {isLoading && <Loader />}
            {!isLoading && product && (<div className="cart">
                <Product data={product} handleAddToCart={props.handleAddToCart} handleAddAlert={props.handleAddAlert} />
                {!ratingChanged && <>
                    <Box component="fieldset" mt={3} mb={3} borderColor="transparent">
                    <Typography component="legend">Rate This Product!</Typography>
                    <Rating name="simple-controlled" value={value}
                        onChange={(e, newValue) => {
                            setValue(newValue);
                        }}
                    />
                </Box>
                <Button variant="contained" color="primary" onClick={handleSubmitRating}>Submit rating</Button>
                </>}
                {
                    ratingChanged && <h3>Your rating has been submitted!</h3>
                }
            </div>)}
        </>
    )

}

export default ProductDetail;