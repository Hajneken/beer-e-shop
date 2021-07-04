import React, { useEffect, useState } from "react";


const History = props => {

    const [loaded, setLoaded] = useState(false);
    const [transactions, setTransactions] = useState([1, 2, 3, 4, 5])

    useEffect(() => {
        fetchTransactions()
        // setTransactions(props.)
    }, [])

    const fetchTransactions = async () => {
        try {
            // const response = await fetch(props.URL);
            const response = await fetch("http://localhost:8089/api/v1/transactions");
            const data = await response.json();
            console.log(data);
            setTransactions(data);
            setLoaded(true);
            // setProducts(data);

        } catch (error) {
            console.log("Error")
        }

    }

    return (
        <div className="cart">
            <h1>Bought Products</h1>
            {loaded && <ol>
                {transactions.map((el, index) => (<li key={index}>
                Transaction ID: <strong>{el.uuid}</strong>
                <br />         
                    <em>Date: {el.createdAt}</em> {" | "}
                    Item: <strong>{el.product} </strong> <br/>  {el.address.firstName} {el.address.lastName}{" | "}
                    Price: <strong>{el.price} </strong>{el.currency}
                </li>))}
            </ol>}
            
        </div>
    )
}

export default History;
