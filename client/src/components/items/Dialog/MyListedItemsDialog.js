import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useState} from "react";
import makeApiCall, {ApiMethod, deleteApiCalls, postApiCalls} from "../../../Utils/ApiUtils";
import {IMAGE_URLS} from "../../../img/Constants";

// This component is used to display the details of an auction item that the user has listed
const MyListedItemDialog = ({open, handleClose, item}) => {
    const [bidAmount, setBidAmount]= useState('');
    const [error, setError] = useState('');

    // This function is called when the user clicks the "Delete Auction" button
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