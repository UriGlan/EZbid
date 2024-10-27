import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useState} from "react";
import makeApiCall, {ApiMethod, postApiCalls} from "../../../Utils/ApiUtils";
import AuctionWonDialog from "./AuctionWonDialog";
import {IMAGE_URLS} from "../../../img/Constants";

// This component is used to display a dialog for placing a bid on an item
const ItemDialog = ({open, handleClose, item}) => {
    const [bidAmount, setBidAmount]= useState('');
    const [error, setError] = useState('')

    // Checking if the auction has ended and the user has won
    const hasAuctionEnded = new Date(item.endTime) < new Date();
    if(hasAuctionEnded && item.currentBid?.bidAmount === item.myBid){
        return (
            <AuctionWonDialog open={open} handleClose={handleClose} item={item}/>
        )
    }
    // Function to validate the bid amount
    const isValidBidAmount = (bidAmount) => {
        const bid = parseFloat(bidAmount);
        const currentBid = item.currentBid ? item.currentBid.bidAmount : item.startingBid;
        if(bid <= currentBid){
            return `Bid amount must be greater than current bid of $${currentBid}`;
        }
        let increment;
        if (currentBid < 100) {
            increment = 5;
        } else if (currentBid < 1000) {
            increment = 50;
        } else if (currentBid < 10000) {
            increment = 100;
        } else if (currentBid < 50000){
            increment = 1000;
        } else {
            increment = 5000;
        }

        if (bid % increment !== 0){
            return `Bid amount must be in increments of $${increment}`;
        }
        return '';
    }
    // Function to handle placing a bid
    const handlePlaceBid = async () => {
        const errorMessage = isValidBidAmount(bidAmount);
        /// If there is an error message, set the error state and return
        if (errorMessage){
            setError(errorMessage);
            return;
        }
        // Call the API to place the bid
        try {
            await postApiCalls(ApiMethod.PLACE_BID, {auction_id: item.auction_id, bidAmount: bidAmount});
            handleClose();
        } catch (error) {
            console.error('Error placing bid:', error);
            const errorMessage = error.response.data.message;
            setError(errorMessage || 'Error placing bid.');
        }
    }
    return(
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Bid on {item.title}</DialogTitle>
            <DialogContent>
                <CardMedia
                    component = 'img'
                    image={item.imageUrl || IMAGE_URLS.DEFAULT_IMAGE}
                    title ={item.title}
                    style = {{height:'auto'}}
                />
                <Typography variant = 'h4' align="center" marginTop='1em' fontWeight='bold'>
                    {item.title}
                </Typography>
                <Typography variant= 'h6' align="center">
                    {item.subtitle}
                </Typography>
                <Typography variant= "body1" fontSize='16px'>
                    {item.description}
                </Typography>
                <Typography fontSize='20px'>
                    <strong>Starting Bid:</strong> ${item.startingBid}
                </Typography>
                <Typography fontSize='20px'>
                    <strong>Current:</strong> ${item.currentBid ? item.currentBid.bidAmount : item.startingBid}
                </Typography>
                {item.myBid ? (
                    <Typography color={item.myBid === item.currentBid.bidAmount ? "green" : "red"} fontSize='20px'>
                        <strong>My Bid:</strong> ${item.myBid}
                    </Typography>
                ) : (<></>)}
                <Typography>
                    <strong>Numbers of bid on current auction:</strong> {item.bidsNumber}
                </Typography>
                <Typography>
                    <strong>Auction Started On:</strong> {new Date(item.startTime).toLocaleString(undefined, {hour12: false})}
                </Typography>
                <Typography marginBottom='1em'>
                    <strong>End Date:</strong> {new Date(item.endTime).toLocaleString(undefined, {hour12: false})}
                    </Typography>
                {item.status === 'Active' ?(
                        <TextField
                        autoFocus
                        margin='dense'
                        label='Place your bid'
                        type='number'
                        fullWidth
                        variant="outlined"
                        value={bidAmount}
                    onChange={(e) => setBidAmount(e.target.value)}
                />): (<></>)}
                {error && <Typography color='error'>{error}</Typography>}
            </DialogContent>
            <DialogActions>
                {item.status === 'Active' ? (<>
                    <Button onClick={handleClose} color="primary">
                    Cancel
                    </Button>
                    <Button onClick={handlePlaceBid} color="primary">
                        Place Bid
                    </Button>
                    </>) : (
                    <Button onClick={handleClose} color="primary">
                        Close
                    </Button>
                    )}
            </DialogActions>
        </Dialog>
    )
};
export default ItemDialog;