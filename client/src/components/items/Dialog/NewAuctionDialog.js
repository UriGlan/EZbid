import React, { useState, useEffect } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, Button, TextField, MenuItem, Select, InputLabel, FormControl } from '@mui/material';
import makeApiCall, { ApiMethod, postApiCalls } from "../../../Utils/ApiUtils";

const NewAuctionDialog = ({ open, onClose, onCreate }) => {
    const [title, setTitle] = useState('');
    const [subtitle, setSubtitle] = useState('');
    const [description, setDescription] = useState('');
    const [startingBid, setStartingBid] = useState('');
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [daysLeft, setDaysLeft] = useState('');
    const [error, setError] = useState('');

    // Fetch categories when the dialog opens
    useEffect(() => {
        if (open) {
            const fetchCategories = async () => {
                try {
                    const data = await makeApiCall(ApiMethod.ALL_CATEGORIES);
                    setCategories(data);
                } catch (error) {
                    console.error("Error fetching categories:", error);
                    setError('Failed to load categories.');
                }
            };
            fetchCategories();
        }
    }, [open]);

    const handleCreate = async () => {
        const newAuction = {
            username: "current_user",  // This will come from the logged-in user
            title,
            subtitle,
            description,
            currentBid: null,  // Assuming this will be set by the backend
            startingBid: parseFloat(startingBid),
            daysLeft: parseInt(daysLeft),
            categoryId: selectedCategory  // Pass selected category to backend
        };
        try {
            await postApiCalls(ApiMethod.NEW_AUCTION, newAuction);
            onCreate(newAuction);
            onClose();
        } catch (error) {
            console.error('Error creating auction:', error);
            const errorMessage = error.response.data.message;
            setError(errorMessage || 'Creation failed.');
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Create New Auction</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Title"
                    type="text"
                    fullWidth
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <TextField
                    margin="dense"
                    label="Subtitle"
                    type="text"
                    fullWidth
                    value={subtitle}
                    onChange={(e) => setSubtitle(e.target.value)}
                />
                <TextField
                    margin="dense"
                    label="Description"
                    type="text"
                    fullWidth
                    value={description}
                    onChange={(e) => {
                        const value = e.target.value;
                        if (value.length <= 1000) {
                            setDescription(value);
                            setError('');
                        } else {
                            setError('Description must not be more than 1000 characters.');
                        }
                    }}
                />
                <TextField
                    margin="dense"
                    label="Starting Bid"
                    type="number"
                    fullWidth
                    value={startingBid}
                    onChange={(e) => setStartingBid(e.target.value)}
                />
                fullWidth
                value={daysLeft}
                onChange={(e) => {
                    const value = e.target.value;
                    if (value >= 1) {
                        setDaysLeft(value);
                        setError('');
                    } else {
                        setError('Auction duration must be at least 1 day.');
                    }
                }}

                {/* Category Dropdown */}
                <FormControl fullWidth margin="dense">
                    <InputLabel id="category-label">Category</InputLabel>
                    <Select
                        labelId="category-label"
                        value={selectedCategory}
                        onChange={(e) => setSelectedCategory(e.target.value)}
                    >
                        {categories.map((category) => (
                            <MenuItem key={category.id} value={category.id}>
                                {category.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

            </DialogContent>
            {error && <DialogContent>{error}</DialogContent>}
            <DialogActions>
                <Button onClick={onClose} color="secondary">Cancel</Button>
                <Button onClick={handleCreate} color="primary">Create</Button>
            </DialogActions>
        </Dialog>
    );
};

export default NewAuctionDialog;
