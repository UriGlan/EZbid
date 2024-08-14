import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import MyBidsDialog from "../../components/items/Dialog/MyBidsDialog";
import Typography from "@mui/material/Typography";

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
                <Typography
                    variant='h2'
                    align='center'
                    paddingTop={2}
                    fontWeight={700}
                   >
                    My Bids
                </Typography>
                <ListItems items={cardList} DialogComponent={MyBidsDialog} />
            </Box>
        </>
            )
}

export default MyBids;