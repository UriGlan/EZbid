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
    const [selectFile, setSelectFile] = useState(null);
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

    // Create a new auction

    const handleCreate = async () => {
        const formData = new FormData();

        // Create the auctionDto object
        const auctionDto = {
            title,
            subtitle,
            description,
            startingBid: parseFloat(startingBid),
            daysLeft: parseInt(daysLeft),
            categoryId: selectedCategory
        };

        // Append the auctionDto as a JSON string
        formData.append('auctionDto', new Blob([JSON.stringify(auctionDto)], { type: 'application/json' }));

        // Append the selected image file (if any)
        if (selectFile) {
            formData.append('img', selectFile);
        }

        try {
            // Send the form data via postApiCalls
            await postApiCalls(ApiMethod.NEW_AUCTION, formData);
            onCreate();  // Call the onCreate handler to update UI or state
            setTitle('');
            setSubtitle('');
            setDescription('');
            setStartingBid('');
            setSelectedCategory('');
            setDaysLeft('');
            setSelectFile(null);
            onClose();
        } catch (error) {
            console.error('Error creating auction:', error);
            const errorMessage = error.response?.data?.message || 'Creation failed.';
            setError(errorMessage);
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
                    onChange={(e) => {
                        const value = e.target.value;
                        if (value >= 1) {
                            setStartingBid(value);
                            setError('');
                        } else {
                            setError('The starting bid must me more than 1$.');
                        }
                    }}
                />
                <TextField
                    margin="dense"
                    label="Days Left"
                    type="number"
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
                />

            {/* Category Dropdown */}

                <FormControl fullWidth margin="dense" variant="outlined">
                    <InputLabel id="category-label" shrink={true}>Category</InputLabel>
                    <Select
                        labelId="category-label"
                        value={selectedCategory}
                        onChange={(e) => setSelectedCategory(e.target.value)}
                        label="Category"
                        displayEmpty
                        renderValue={(selected) => {
                            if (selected.length === 0) {
                                return <span style={{ color: 'gray' }}>Select Category</span>;
                            }
                            return categories.find(category => category.id === selected)?.name;
                        }}
                    >
                        {categories.map((category) => (
                            <MenuItem key={category.id} value={category.id}>
                                {category.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                {/* File input for image upload */}
                <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => {
                        const file = e.target.files[0];
                        if(file){
                            setSelectFile(file);
                        }
                        console.log("file uploaded" + file);
                    }}
                />

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
