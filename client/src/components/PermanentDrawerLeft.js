import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import VariantAppBar from "../components/menu/VariantAppBar";
import ListItems from "../components/items/ListItems";
import {AccountCircle} from "@mui/icons-material";
import GavelIcon from '@mui/icons-material/Gavel';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';

const drawerWidth = 240;

const drawerItems = [
    { text: 'My Bids', icon: <GavelIcon /> },
    { text: 'My Listed Items', icon: <AccountBalanceWalletIcon /> },
    { text: 'Profile', icon: <AccountCircle /> },
];
const PermanentDrawerLeft = () => {
    return (
        <>
            <VariantAppBar/>
            <Box sx={{ display: 'flex', marginTop:'70px' }}>
                <CssBaseline />
                <Drawer
                    sx={{
                        width: drawerWidth,
                        flexShrink: 0,
                        '& .MuiDrawer-paper': {
                            width: drawerWidth,
                            boxSizing: 'border-box',
                            top: 'auto',
                        },
                    }}
                    variant="permanent"
                    anchor="left"
                >
                    <List>
                        {drawerItems.map((item, index) => (
                            <ListItem key={item.text} disablePadding>
                                <ListItemButton>
                                    <ListItemIcon>
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={item.text} />
                                </ListItemButton>
                            </ListItem>
                        ))}
                    </List>
                    <Divider />
                </Drawer>

            </Box>
        </>
    );
}

export default PermanentDrawerLeft