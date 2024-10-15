import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useState} from "react";
import makeApiCall, {ApiMethod, postApiCalls} from "../../../Utils/ApiUtils";
import AuctionWonDialog from "./AuctionWonDialog";


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

    const handlePlaceBid = async () => {
        const errorMessage = isValidBidAmount(bidAmount);
        if (errorMessage){
            setError(errorMessage);
            return;
        }
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
                    image = "https://images.unsplash.com/photo-1586796304259-5fa44d5e3f71?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    title ={item.title}
                    style = {{height:'auto'}}
                />
                <Typography variant = 'h5'>
                    {item.title}
                </Typography>
                <Typography variant= 'h6'>
                    {item.subtitle}
                </Typography>
                <Typography variant= "body2">
                    {item.description}
                </Typography>
                <Typography>
                    <strong>Starting Bid:</strong> ${item.startingBid}
                </Typography>
                <Typography>
                    <strong>Current:</strong> ${item.currentBid ? item.currentBid.bidAmount : item.startingBid}
                </Typography>
                {item.myBid ? (
                    <Typography color={item.myBid === item.currentBid ? "green" : ""}>
                        <strong>My Bid:</strong> ${item.myBid}
                    </Typography>
                ) : (<></>)}
                <Typography>
                    <strong>Numbers of bid on current auction:</strong> {item.bidsNumber}
                </Typography>
                <Typography>
                    <strong>Auction Started On:</strong> {new Date(item.startTime).toLocaleString(undefined, {hour12: false})}
                </Typography>
                <Typography>
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