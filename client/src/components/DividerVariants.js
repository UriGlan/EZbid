import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';

const style = {
    py: 0,
    width: '60%',
    borderRadius: 2,
    border: '1px solid',
    borderColor: 'divider',
    backgroundColor: 'background.paper',
    alignItems: 'center',
};

const DividerVariants = (profile) => {
    console.log(profile);
    return (
        <List sx={style}>
            <ListItem>
                <ListItemText primary="User Name" />
                <ListItemText primary={profile.profile.username} />
            </ListItem>
            <Divider variant="middle" component="li" />
            <ListItem>
                <ListItemText primary="First Name" />
                <ListItemText primary={profile.profile.firstName} />

            </ListItem>
            <Divider variant="middle" component="li" />
            <ListItem>
                <ListItemText primary="Last Name" />
                <ListItemText primary={profile.profile.lastName} />

            </ListItem>
            <Divider variant="middle" component="li" />
            <ListItem>
                <ListItemText primary="Email Address" />
                <ListItemText primary={profile.profile.email} />

            </ListItem>

        </List>
    );
}
export default DividerVariants;
