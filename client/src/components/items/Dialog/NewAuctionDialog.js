import React, { useState } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, Button, TextField } from '@mui/material';
import makeApiCall, {ApiMethod, postApiCalls} from "../../../Utils/ApiUtils";

const NewAuctionDialog = ({ open, onClose, onCreate }) => {
    const [title, setTitle] = useState('');
    const [subtitle, setSubtitle] = useState('');
    const [description, setDescription] = useState('');
    const [startingBid, setStartingBid] = useState('');
    const [error, setError] = useState('');

    const handleCreate = async () => {

        const newAuction = {
            username: "current_user",  // This will come from the logged-in user
            title,
            subtitle,
            description,
            currentBid: null,  // Assuming this will be set by backend
            startingBid: parseFloat(startingBid),
        };
        try {
            await postApiCalls(ApiMethod.NEW_AUCTION, newAuction);
            onCreate(newAuction);

        } catch (error) {
            console.error('Error updaing profile:', error);
            const errorMessage = error.response.data.message;
            setError(errorMessage || 'Update failed.');
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
                    onChange={(e) => setDescription(e.target.value)}
                />
                <TextField
                    margin="dense"
                    label="Starting Bid"
                    type="number"
                    fullWidth
                    value={startingBid}
                    onChange={(e) => setStartingBid(e.target.value)}
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
