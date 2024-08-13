import React from "react";
import Grid from "@mui/material/Grid";
import CardItem from "./CardItem";
const ListItems = ({ items }) => {
    return (
        <Grid container spacing={4}>
            {items.map((item, index) => (
                <Grid item xs={12} sm={6} md={3} key={item.id || index}>
                    <CardItem item={item} />
                </Grid>
            ))}
        </Grid>
    );
};

export default ListItems;
