import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import ItemDialog from "../../components/items/ItemDialog";
import MyBidsDialog from "../../components/items/MyBidsDialog";

const cardList = [
    { id: 1, name: "A cute Dog" },
    { id: 2, name: "A very cute Dog" },
    { id: 3, name: "Snoop Dog" },
    { id: 4, name: "The Smartest Dog" },
];

const MyBids = () => {
    return (
        <>
            <PermanentDrawerLeft />
            <Box
                component="main"
                sx={{ flexGrow: 1, paddingLeft: 35, paddingRight: 7 }}
            >
                <ListItems items={cardList} DialogComponent={MyBidsDialog} />
            </Box>
        </>
            )
}

export default MyBids;