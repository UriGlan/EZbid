import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useState} from "react";
import makeApiCall, {ApiMethod, postApiCalls} from "../../../Utils/ApiUtils";


const ItemDialog = ({open, handleClose, item}) => {
    console.log(item);
    const [bidAmount, setBidAmount] = useState('');
    const handlePlaceBid = () => {
        postApiCalls(ApiMethod.PLACE_BID, {auction_id: item.auction_id, bidAmount:bidAmount});
        handleClose();
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
                <Typography variant= 'h6'>\
                    {item.subtitle}
                </Typography>
                <Typography variant= "body2">
                    {item.description}
                </Typography>
                <Typography variant= 'h7'>
                    <strong>Seller:</strong> {item.username}
                </Typography>
                <Typography>
                    <strong>Starting bid:</strong> ${item.startingBid}
                </Typography>
                <Typography>
                    <strong>Current :</strong> ${item.currentBid ? item.currentBid.bidAmount : item.startingBid}
                </Typography>
                <TextField
                autoFocus
                margin='dense'
                label='Place your bid'
                type='number'
                fullWidth
                variant="outlined"
                value={bidAmount}
                onChange={(e) => setBidAmount(e.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Cancel
                </Button>
                <Button onClick={handlePlaceBid} color="primary">
                    Place Bid
                </Button>
            </DialogActions>
        </Dialog>
    )
};
export default ItemDialog;