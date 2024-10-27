import React, { useState, useEffect } from 'react';
import axios from 'axios';


// This component will fetch all auctions from the server and display them in a list.
const Auction = () => {
    const [auctions, setAuctions] = useState([]);
    useEffect(() => {
        axios.get('http://localhost:8080/auctions/all', {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        })
            .then(response => {
                // handle the response data
                setAuctions(response.data);
            })
            .catch((error) => {
                console.error('Error fetching data: ', error);
            });
    }, []);

    return (
        <div>
            <h1>Auctions</h1>
            <ul>
                {auctions.map(auction => (
                    <li key={auction.auction_id}>
                        {auction.title} - {auction.description}
                    </li>
                ))}
            </ul>
        </div>
    );
}
export default Auction;