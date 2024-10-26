import React, { useEffect, useState } from "react";
import {
    Typography,
    CssBaseline,
    Container,
    MenuItem,
    Select,
    FormControl,
    InputLabel,
    Box
} from "@mui/material";
import VariantAppBar from "../components/menu/VariantAppBar";
import Footer from "../components/Footer";
import ListItems from "../components/items/ListItems";
import makeApiCall, { ApiMethod } from "../Utils/ApiUtils";
import MyBidsDialog from "../components/items/Dialog/ItemDialog";

const Home = () => {
    const [auctions, setAuctions] = useState([]);
    const [filteredAuctions, setFilteredAuctions] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("All");
    const [userBids, setUserBids] = useState([]);

    // Fetch User Bids
    const fetchUserBids = async () => {
        try {
            const data = await makeApiCall(ApiMethod.MY_BIDS); // Assuming you have this API
            if (Array.isArray(data)) {
                setUserBids(data.map(bid => ({
                    auctionId: bid.auction.auction_id,
                    myBid: bid.bidAmount
                })));
            } else {
                console.error('Expected array, but got', data);
                setUserBids([]);
            }
        } catch (error) {
            console.error('Error fetching user bids:', error);
            setUserBids([]);
        }
    }

    // Fetch Auctions
    const fetchAuctions = async () => {
        try {
            const data = await makeApiCall(ApiMethod.ALL_AUCTIONS); // Call the Api for fetching auctions
            console.log('Fetched auctions:', data);
            if (Array.isArray(data)) {
                const activeAuctions = data
                    .filter(auction => auction.status === 'Active')
                    .sort((a, b) => new Date(a.endTime) - new Date(b.endTime));

                // Mark the auctions where the user has placed a bid
                const updatedAuctions = activeAuctions.map(auction => {
                    const userBid = userBids.find(bid => bid.auctionId === auction.auction_id);
                    if (userBid) {
                        return { ...auction, myBid: userBid.myBid };
                    }
                    return auction;
                });

                setAuctions(updatedAuctions);
            } else {
                console.error('Expected array, but got', data);
                setAuctions([]);
            }
        } catch (error) {
            console.error('Error fetching auctions:', error);
            setAuctions([]);
        }
    };

    // Fetch Categories
    const fetchCategories = async () => {
        try {
            const data = await makeApiCall(ApiMethod.ALL_CATEGORIES); // Assuming you have this API
            if (Array.isArray(data)) {
                setCategories([{ name: "All", id: "All" }, ...data]); // Add 'All' as the first option
            } else {
                console.error('Expected array, but got', data);
                setCategories([]);
            }
        } catch (error) {
            console.error('Error fetching categories:', error);
            setCategories([]);
        }
    };

    // Apply Filter by Category
    const applyCategoryFilter = () => {
        if (selectedCategory === "All") {
            setFilteredAuctions(auctions); // Show all auctions
        } else {
            setFilteredAuctions(auctions.filter(auction => auction.categoryId && auction.categoryId === selectedCategory));
        }
    };

    // Handle Category Change
    const handleCategoryChange = (event) => {
        const categoryId = event.target.value;
        setSelectedCategory(categoryId);
    };

    useEffect(() => {
        fetchUserBids();
        fetchAuctions();
        fetchCategories();
    }, [userBids]);

    useEffect(() => {
        applyCategoryFilter();
    }, [auctions, selectedCategory]);

    return (
        <>
            <CssBaseline />
            <VariantAppBar />
            <main>
                <Box sx={{ backgroundColor: '#d7d7d7', height: 'auto', paddingTop: '80px', paddingBottom: '15%' }}>

                    <Container maxWidth="sm">
                        <Typography variant="h2" align="center" color="textPrimary" gutterBottom sx={{ fontWeight: "bold" }}>
                            Listed Items
                        </Typography>
                    </Container>

                    {/* Dropdown to Filter by Category */}
                    <Container>
                        <FormControl sx={{ width: '20%' }}>
                            <InputLabel>Category</InputLabel>
                            <Select
                                value={selectedCategory}
                                onChange={handleCategoryChange}
                                label="Category"
                            >
                                {categories.map((category, index) => (
                                    <MenuItem key={index} value={category.id}>
                                        {category.name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Container>

                    {/* List Items */}
                    <Container>
                        <ListItems items={filteredAuctions} DialogComponent={MyBidsDialog} />
                    </Container>
                </Box>
            </main>
            <Footer />
        </>
    );
};

export default Home;
