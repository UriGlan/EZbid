import { Button, Card, CardActions, CardContent, CardMedia, Typography } from "@mui/material";
import React, { useState } from "react";

const CardItem = ({ item, DialogComponent, handleDeleteItem }) => {
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <>
            <Card style={{ height: 'auto', display: 'flex', flexDirection: 'column', marginTop: '3em' }}>
                <CardMedia
                    component="img"
                    image={item.imageUrl || "https://images.unsplash.com/photo-1586796304259-5fa44d5e3f71?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"}
                    title={item.title}
                />
                <CardContent style={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5">
                        {item.title}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                        {item.description}
                    </Typography>
                    {item.currentBid ? (
                        <Typography variant="body1" color="textPrimary">
                            Current Bid: ${item.currentBid.bidAmount}
                        </Typography>
                    ) : (
                        <Typography variant="body1" color="textPrimary">
                            Current Bid Bid: ${item.startingBid}
                        </Typography>
                    )}
                </CardContent>
                <CardActions>
                    <Button size="small" color="primary" onClick={handleClickOpen}>
                        View Details
                    </Button>
                </CardActions>
            </Card>
            {DialogComponent && (
                <DialogComponent open={open} handleClose={handleClose} item={item} handleDeleteItem ={handleDeleteItem}/>
            )}
        </>
    )
};

export default CardItem;
