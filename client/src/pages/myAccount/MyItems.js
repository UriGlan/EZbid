import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import Typography from "@mui/material/Typography";
import MyListedItemsDialog from "../../components/items/Dialog/MyListedItemsDialog";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import {useState} from "react";


const MyItems = () => {
        const [cardList, setCardList] = useState([
            { id: 1, name: "A cute Dog" },
            { id: 2, name: "A very cute Dog" },
            { id: 3, name: "Snoop Dog" },
            { id: 4, name: "The Smartest Dog" },
        ]);

        const handleNewItem = () => {
            const newItem = {
                id: cardList.length + 1, // generate a new id
                name: `New Item ${cardList.length + 1}`, // simple name generation
            };
            setCardList([...cardList, newItem]); // add the new item to the list
        };
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
                    My Listed Items
                </Typography>
                <ListItems items={cardList} DialogComponent={MyListedItemsDialog} />
                <Grid
                    container
                    justifyContent="center"
                    sx={{ mt: 4, paddingBottom: 10, paddingTop: 2 }}
                >
                    <Button onClick={handleNewItem} variant="contained" color="primary">
                        Add new item +
                    </Button>
                </Grid>
            </Box>
        </>
    )
}

export default MyItems;