import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import makeApiCall, {ApiMethod, postApiCalls} from "../../../Utils/ApiUtils";
import {useState} from "react";

const AuctionWonDialog = ({ open, handleClose, item }) => {

    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Congratulations! You won the auction</DialogTitle>
            <DialogContent>
                <CardMedia
                    component = 'img'
                    image={item.imageUrl || "https://images.unsplash.com/photo-1586796304259-5fa44d5e3f71?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"}
                    title ={item.title}
                    style = {{height:'auto'}}
                />
                <Typography variant='h5'>
                    {item.title}
                </Typography>
                <Typography variant='h6'>
                    {item.subtitle}
                </Typography>
                <Typography variant="body2">
                    {item.description}
                </Typography>
                <Typography>
                    <strong>Your Winning Bid:</strong> ${item.currentBid?.bidAmount}
                </Typography>
                <Typography>
                    <strong>End Date:</strong> {new Date(item.endTime).toLocaleString(undefined, { hour12: false })}
                </Typography>
                <Typography>
                    <strong>Seller Contact Information:</strong>
                </Typography>
                <Typography>
                    <strong>Seller Email Address:</strong> {item.email}
                </Typography>
                <Typography>
                    <strong>Seller First Name:</strong> {item.firstName}
                </Typography>
                <Typography>
                    <strong>Seller Last Name:</strong> {item.lastName}
                </Typography>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default AuctionWonDialog;
