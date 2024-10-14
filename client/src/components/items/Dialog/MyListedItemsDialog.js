import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useState} from "react";
import makeApiCall, {ApiMethod, deleteApiCalls, postApiCalls} from "../../../Utils/ApiUtils";


const MyListedItemDialog = ({open, handleClose, item}) => {
    const [bidAmount, setBidAmount]= useState('');
    const [error, setError] = useState('')
    const handleDelete = async () => {
        try {
            await deleteApiCalls(`${ApiMethod.DELETE_AUCTION}${item.auction_id}`); // Include auction ID in the URL
            handleClose();
        } catch (error) {
            console.error('Error deleting auction:', error);
            const errorMessage = error.response?.data?.message;
            setError(errorMessage || 'Error deleting auction.');
        }
    };

    return(
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Auction: {item.title}</DialogTitle>
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
                {error && <Typography color='error'>{error}</Typography>}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Close
                </Button>
                <Button onClick={handleDelete} color="primary">
                    Delete Auction
                </Button>
            </DialogActions>
        </Dialog>
    )
};
export default MyListedItemDialog;