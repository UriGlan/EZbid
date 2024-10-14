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
import SearchItem from "../components/items/SearchItem";
import Footer from "../components/Footer";
import ListItems from "../components/items/ListItems";
import makeApiCall, { ApiMethod } from "../Utils/ApiUtils";
import MyBidsDialog from "../components/items/Dialog/ItemDialog";

const Home = () => {
    const [auctions, setAuctions] = useState([]);
    const [filteredAuctions, setFilteredAuctions] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("All");

    // Fetch Auctions
    const fetchAuctions = async () => {
        try {
            const data = await makeApiCall(ApiMethod.ALL_AUCTIONS);
            if (Array.isArray(data)) {
                setAuctions(data);
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
                setCategories([{name: "All", id: "All"}, ...data]); // Add 'All' as the first option
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
            setFilteredAuctions(auctions.filter(auction => auction.category.name === selectedCategory));
        }
    };

    // Handle Category Change
    const handleCategoryChange = (event) => {
        const category = event.target.value;
        setSelectedCategory(category);
    };

    useEffect(() => {
        fetchAuctions();
        fetchCategories();
        const intervalId = setInterval(fetchAuctions, 2000);
        return () => clearInterval(intervalId);
    }, []);

    // Apply the filter when auctions or selectedCategory changes
    useEffect(() => {
        applyCategoryFilter();
    }, [auctions, selectedCategory]);

    return (
        <>
            <CssBaseline />
            <VariantAppBar />
            <main>
                <Box sx={{ backgroundColor: '#d7d7d7', height: 'auto', paddingTop: '80px', paddingBottom: '15%' }}>
                    <div>
                        <SearchItem />
                    </div>
                    <Container maxWidth="sm">
                        <Typography variant="h2" align="center" color="textPrimary" gutterBottom sx={{ fontWeight: "bold" }}>
                            Listed Items
                        </Typography>
                    </Container>

                    {/* Dropdown to Filter by Category */}
                    <Container>
                        <FormControl sx={{width: '20%'}}>
                            <InputLabel>Category</InputLabel>
                            <Select
                                value={selectedCategory}
                                onChange={handleCategoryChange}
                                label="Category"
                            >
                                {categories.map((category, index) => (
                                    <MenuItem key={index} value={category.name}>
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
