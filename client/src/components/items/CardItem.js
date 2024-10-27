import { Button, Card, CardActions, CardContent, CardMedia, Typography } from "@mui/material";
import React, { useState } from "react";
import {IMAGE_URLS} from "../../img/Constants";

// This component is used to display a single item in a card format

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
                height: '100%',
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
                    <Typography gutterBottom variant="h4" align="center">
                        {item.title}
                    </Typography>
                    <Typography variant="subtitle2" color="textSecondary" align="center" fontSize="18px">
                        {item.subtitle}
                    </Typography>

                    <Typography variant="body2" color={item.active ? "green" : item.bidsNumber === 0 ? "red" : "#dc5b03"} align="center">
                        Item status: {item.active ? "Active" : item.bidsNumber === 0 ? "Expired" : "Sold"}
                    </Typography>

                    <Typography variant="body1" color="textPrimary" marginTop="1em">
                        Current Bid: ${bidAmount}
                    </Typography>

                    {item.myBid ? (
                        <Typography variant="body1" color="purple">
                            Your bid: ${item.myBid}
                        </Typography>
                    ) : (
                        <Typography variant="body1" color="gray">
                            You haven't placed a bid
                        </Typography>
                    )}

                    <Typography variant="h6" color="green" align="center" fontWeight="bold"
                    sx={{
                        marginTop: '2em',
                    }}>
                        {item.currentBid?.bidAmount === item.myBid && item.status === 'Won' ? 'You won this auction!' : ''}
                    </Typography>

                </CardContent>
                <CardActions style={{marginTop:'auto'}}>
                    <Button  color="primary" onClick={handleClickOpen} sx={{
                        backgroundColor: '#748eeb',
                        color: 'white',
                        width:'-webkit-fill-available',
                        borderRadius: '9px',
                        '&:hover': {
                            backgroundColor: '#5a7ddb', // Change this to your desired hover color
                        }
                    }}>
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
