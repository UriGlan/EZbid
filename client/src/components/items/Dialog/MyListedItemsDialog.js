import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";

const MyListedItemsDialog = ({open, handleClose, item, handleDeleteItem}) => {
    const handleDelete = () => {
        handleDeleteItem(item.id);
        handleClose();
    };
    return(
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Bid on {item.name}</DialogTitle>
            <DialogContent>
                <CardMedia
                    component = 'img'
                    image = "https://images.unsplash.com/photo-1586796304259-5fa44d5e3f71?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    title ={item.name}
                    style = {{height:'auto'}}
                />
                <Typography variant = 'h6'>
                    Item Name {item.name}
                </Typography>
                <Typography paragraph>
                    Description: {item.description}<br/>
                    Start price: <br/>
                    Current bid:<br/>
                    End Date: <br/>
                    Date Added:

                </Typography>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleDelete} color="primary">
                    Delete Item
                </Button>
                <Button onClick={handleClose} color="primary">
                    Close
                </Button>
            </DialogActions>
        </Dialog>
    )
};
export default MyListedItemsDialog;