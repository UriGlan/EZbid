import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";

// Profile Edit Components
const ProfileEditComponents = ({profile,onChange}) => {

    const fields = [
        { id: 'username', label: 'User Name', disabled: true, placeholder: profile.username },
        { id: 'firstName', label: 'First Name', disabled: false, placeholder: profile.firstName },
        { id: 'lastName', label: 'Last Name', disabled: false, placeholder: profile.lastName },
        { id: 'email', label: 'Email Address', disabled: true, placeholder: profile.email },
    ];
    return (
        <Grid container spacing={2}>
            {fields.map((field) => (
                <Grid item xs={12} key={field.id}>
                    <TextField
                        id={field.id}
                        label={field.label}
                        disabled={field.disabled}
                        value={field.placeholder || ''}
                        onChange={(e) => onChange(e.target.id, e.target.value)}

                        variant="outlined"
                        fullWidth
                        InputProps={{
                            sx: {
                                color: 'black', // Change input text color
                                '& .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'black', // Change border color
                                },
                                '&:hover .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'darkgreen', // Change border color on hover
                                },
                                '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'purple', // Change border color when focused
                                },
                                '&::placeholder': {
                                    color: 'black', // Change label color
                                },

                            },
                        }}
                        InputLabelProps={{
                            sx: {
                                color: 'black', // Change label color
                                '&.Mui-focused': {
                                    color: 'purple', // Change label color when focused
                                },

                            },
                            shrink: true,
                        }}
                    />
                </Grid>
            ))}
        </Grid>
    );
};

export default ProfileEditComponents;
