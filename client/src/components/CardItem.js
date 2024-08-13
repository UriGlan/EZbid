import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";
import React, {useState} from "react";
import ItemDialog from "./ItemDialog";

const CardItem =({item}) =>{
    const [open, setOpen] = useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    }
    const handleClose = () => {
        setOpen(false);
    }
    return(
        <>
            <Card style={{ height: 'auto', display: 'flex', flexDirection: 'column',marginTop: '3em' }}>
                <CardMedia
                    component="img"
                    image="https://images.unsplash.com/photo-1586796304259-5fa44d5e3f71?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    title={item.name}
                />
                <CardContent style={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5">
                        Item name
                    </Typography>
                    <Typography>
                        This will be the description of {item.name}.
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small" color="primary" onClick={handleClickOpen}>
                        View
                    </Button>
                </CardActions>
            </Card>
            <ItemDialog open={open} handleClose={handleClose} item={item} />
        </>
    )
};

export default CardItem;