import React, { useEffect, useState } from "react";

import Product from "../../components/product";
import Loader from "../../components/loader";
import { api } from "../../api";



const Products = props => {
    const [products, setProducts] = useState(null);
    const [loaded, setLoaded] = useState(false);

    const fetchProducts = async () => {
        try {
            const data = await api.getProducts();
            setProducts(data);
            setLoaded(true);
        } catch (error) {
            console.error("Error")
        }

    }

    useEffect(() => {
        fetchProducts();
    }, [])

    return (
        <>
            {!loaded && <Loader />}
            {loaded && (<>{products.map(product => <Product key={product.uuid} handleAddToCart={props.handleAddToCart} handleAddAlert={props.handleAddAlert} data={product} />)}</>)}
        </>
    )
}

export default Products;