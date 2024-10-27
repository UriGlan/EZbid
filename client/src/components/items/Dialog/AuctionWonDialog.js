import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {IMAGE_URLS} from "../../../img/Constants";

// This component is used to display a dialog when the user wins an auction
const AuctionWonDialog = ({ open, handleClose, item }) => {

    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle variant='h5' fontWeight='bold' align='center' color='green'>
                Congratulations! You won the auction
            </DialogTitle>
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
                <Typography fontSize='20px' align='center' color='#dc5b03' border='solid' borderRadius='9px' margin='1em'>
                    <strong>Your Winning Bid:</strong> ${item.currentBid?.bidAmount}
                </Typography>
                <Typography>
                    <strong>End Date:</strong> {new Date(item.endTime).toLocaleString(undefined, { hour12: false })}
                </Typography>
                <Typography variant='h6' align= "center" marginTop='2em'>
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
