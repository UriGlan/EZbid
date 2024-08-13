import {CardMedia, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import {useState} from "react";

const ItemDialog = ({open, handleClose, item}) => {
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
                    Item Description
                </Typography>
                <Typography paragraph>
                    Here you can place a bid on {item.name}
                </Typography>
                <TextField
                    autoFocus
                    margin = 'dense'
                    label = 'Place your bid'
                    type = 'number'
                    fullWidth
                    variant="outlined"
                    />
            </DialogContent>
            <DialogActions>
                <button onClick={handleClose} color="primary">
                    Cancel
                </button>
                <button onClick={handleClose} color="primary">
                    Place Bid
                </button>
            </DialogActions>
        </Dialog>
    )
};
export default ItemDialog;