import { Button, Card, CardActions, CardContent, CardMedia, Typography } from "@mui/material";
import React, { useState } from "react";
import {IMAGE_URLS} from "../../img/Constants";

const CardItem = ({ item, DialogComponent, handleDeleteItem }) => {
    console.log(item);
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const bidAmount = item.currentBid ? item.currentBid.bidAmount : item.startingBid;

    return (
        <>

            <Card style={{
                backgroundColor:'#f1f1f1',
                height: '28em',
                display: 'flex',
                flexDirection: 'column',
                marginTop: '3em',
                borderRadius: '1em',
            }}>
                <CardMedia
                    component="img"
                    image={item.imageUrl || IMAGE_URLS.DEFAULT_IMAGE}
                    title={item.title}
                    style={{ height: '11em', objectFit: 'cover' }}
                />
                <CardContent style={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5">
                        {item.title}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                        {item.subtitle}
                    </Typography>

                    <Typography variant="body1" color="textPrimary">
                        Current Bid: ${bidAmount}
                    </Typography>

                    {item.myBid ? (
                        <Typography variant="body1" color="green">
                            You placed a bid: ${item.myBid}
                        </Typography>
                    ) : (
                        <Typography variant="body1" color="gray">
                            You haven't placed a bid on this auction
                        </Typography>
                    )}

                    <Typography variant="body2" color={item.active ? "green" : item.bidsNumber === 0 ? "red" : "orange"}>
                        {item.active ? "Active" : item.bidsNumber === 0 ? "Expired" : "Sold"}
                    </Typography>
                    <Typography variant="body1" color="green">
                        {item.currentBid?.bidAmount === item.myBid && item.status === 'Won' ? 'You won this auction!' : ''}
                    </Typography>

                </CardContent>
                <CardActions>
                    <Button size="small" color="primary" onClick={handleClickOpen}>
                        View Details
                    </Button>
                </CardActions>
            </Card>
            {DialogComponent && (
                <DialogComponent open={open} handleClose={handleClose} item={item} handleDeleteItem={handleDeleteItem} />
            )}
        </>
    )
};

export default CardItem;
