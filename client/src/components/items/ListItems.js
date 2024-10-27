import React from "react";
import Grid from "@mui/material/Grid";
import CardItem from "./CardItem";
import Typography from "@mui/material/Typography";

// ListItems component

const ListItems = ({ items,DialogComponent, handleDeleteItem }) => {
    if (!Array.isArray(items)){
        return  <Typography>
                    No items found
                </Typography>
    }
    return (
        <Grid container spacing={4}>
            {items.map((item, index) => (
                <Grid item xs={12} sm={6} md={3} key={item.id || index}>
                    <CardItem item={item} DialogComponent={DialogComponent} handleDeleteItem={handleDeleteItem}  />
                </Grid>
            ))}
        </Grid>
    );
};

export default ListItems;
