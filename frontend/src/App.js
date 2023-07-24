import "./App.css";
import { purple } from '@mui/material/colors';
import { styled } from '@mui/material/styles';
import axios from 'axios'
import { Checkbox } from "@mui/material";
import Slider from "@mui/material/Slider";
import { useState } from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import { TextField } from "@mui/material";
import {Button} from "@mui/material"
import LinearProgress from '@mui/material/LinearProgress'
import { createTheme, ThemeProvider } from "@mui/material";
export const muiTheme = createTheme({
  components: {
      MuiCheckbox: {
          styleOverrides: {
              root: {
                  color: '#9c27b0',
                  '&.Mui-checked': {
                    color: '#9c27b0', // Set the desired color when the checkbox is checked
                  },
              },
          },
      },
  },
});
function App() {
  const [movie, setMovie] = useState('')
  const [isMovie, setIsMovie] = useState(false)
  const [isLoading, setIsLoading] = useState(false)
  const [rating, setRating] = useState([0, 10]);
  const [year, setYear] = useState([1930, 2023]);
  const [length, setLength] = useState([0, 240]);
  const [actionChecked, setActionChecked] = useState(false)
  const [adventureChecked, setAdventureChecked] = useState(false)
  const [animatedChecked, setAnimatedChecked] = useState(false)
  const [comedyChecked, setComedyChecked] = useState(false)
  const [dramaChecked, setDramaChecked] = useState(false)
  const [fantasyChecked, setFantasyChecked] = useState(false)
  const [historicalChecked, setHistoricalChecked] = useState(false)
  const [horrorChecked, setHorrorChecked] = useState(false)
  const [noirChecked, setNoirChecked] = useState(false)
  const [musicalChecked, setMusicalChecked] = useState(false)
  const [romanceChecked, setRomanceChecked] = useState(false)
  const [sciencefictionChecked, setSciencefictionChecked] = useState(false)
  const [thrillerChecked, setThrillerChecked] = useState(false)
  const [westernChecked, setWesternChecked] = useState(false)
  const [cast, setCast] = useState('')

  const gatherGenres = () => {
    let genres = []
    let res = ''
    if (actionChecked) {
      genres.push('action, ')
    }
    if (adventureChecked) {
      genres.push('adventure, ')
    }
    if (animatedChecked) {
      genres.push('animated, ')
    }
    if (comedyChecked) {
      genres.push('comedy, ')
    }
    if (dramaChecked) {
      genres.push('drama, ')
    }
    if (fantasyChecked) {
      genres.push('fantasy, ') 
    }
    if (historicalChecked) {
      genres.push('historical, ') 
    }
    if (horrorChecked) {
      genres.push('horror, ')
    }
    if (noirChecked) {
      genres.push('noir, ')
    }
    if (musicalChecked) {
      genres.push('musical, ')
    }
    if (romanceChecked) {
      genres.push('romance, ')
    }
    if (sciencefictionChecked) {
      genres.push('science fiction, ')
    }
    if (thrillerChecked) {
      genres.push('thriller, ')
    }
    if (westernChecked) {
      genres.push('western, ')
    }
    genres.forEach((genre) => res += genre)
    return res
  }

  

  const submitParams = async () => {
    setIsLoading(true)
    setIsMovie(false)
    console.log(isLoading)
    await axios.post('http://localhost:8080/movies/movie', 
    {
      rating:`${rating[0]}-${rating[1]}`,
      releaseYear:`${year[0]}-${year[1]}`,
      filmLength:`${length[0]}-${length[1]}`,
      genre:gatherGenres(),
      desiredCast:`${cast}`
    }
    ).then(response => {
      setMovie(response.data)
      setTimeout(function(){setIsLoading(false); setIsMovie(true)}, 1000)
    })
    .catch(error => {
      console.log(error);
    })
    
    
  }
  const handleRatingChange = (event, newValue) => {
    setRating(newValue);
  };
  const handleYearChange = (event, newValue) => {
    setYear(newValue);
  };
  const handleLengthChange = (event, newValue) => {
    setLength(newValue);
  };
  const handleActionChange = (event, newValue) => {
    setActionChecked(newValue)
  }
  const handleAdventureChange = (event, newValue) => {
    setAdventureChecked(newValue)
  }
  const handleAnimatedChange = (event, newValue) => {
    setAnimatedChecked(newValue)
  }
  const handleComedyChange = (event, newValue) => {
    setComedyChecked(newValue)
  }
  const handleDramaChange = (event, newValue) => {
    setDramaChecked(newValue)
  }
  const handleFantasyChange = (event, newValue) => {
    setFantasyChecked(newValue)
  }
  const handleHistoricalChange = (event, newValue) => {
    setHistoricalChecked(newValue)
  }
  const handleHorrorChange = (event, newValue) => {
    setHorrorChecked(newValue)
  }
  const handleMusicalChange = (event, newValue) => {
    setMusicalChecked(newValue)
  }
  const handleNoirChange = (event, newValue) => {
    setNoirChecked(newValue)
  }
  const handleRomanceChange = (event, newValue) => {
    setRomanceChecked(newValue)
  }
  const handleScienceFictionChange = (event, newValue) => {
    setSciencefictionChecked(newValue)
  }
  const handleThrillerChange = (event, newValue) => {
    setThrillerChecked(newValue)
  }
  const handleWesternChange = (event, newValue) => {
    setWesternChecked(newValue)
  }
  const ColorButton = styled(Button)(({ theme }) => ({
    color: theme.palette.getContrastText(purple[500]),
    backgroundColor: purple[500],
    '&:hover': {
      backgroundColor: purple[700],
    },
  }));
  return (
    <div className="App">
      <div className={`${isLoading ? 'loading' : 'hidden'}`}>
        <h1 className="loader-title">Loading...</h1>
        <LinearProgress color="secondary"/>
      </div>
      <div className={`${isMovie ? 'movieShowcase' : 'hidden'}`}>
        <h1>Your movie:</h1>
        <h2 className="movieText">{movie}</h2>
      </div>
      <div className={`${(isLoading || isMovie) ? 'hidden' : ''}`}>
        <h1 className="header">Choose the parameters for your movie:</h1>
        <div className="slider-container">
          <h3>Choose the rating of the movie:</h3>
          <Slider
            color="secondary"
            valueLabelDisplay="auto"
            step={1}
            marks
            value={rating}
            onChange={handleRatingChange}
            min={0}
            max={10}
            disableSwap
          />
        </div>
        <div className="slider-container">
          <h3>Choose the release year of the movie:</h3>
          <Slider
            color="secondary"
            valueLabelDisplay="auto"
            value={year}
            onChange={handleYearChange}
            min={1930}
            max={2023}
            disableSwap
          />
        </div>
        <div className="slider-container">
          <h3>Choose the film length of the movie (in minutes):</h3>
          <Slider
            color="secondary"
            valueLabelDisplay="auto"
            value={length}
            onChange={handleLengthChange}
            step={5}
            min={45}
            max={240}
            disableSwap
          />
        </div>
        <div className="genre-container">
          <h3>Choose the genre of the film:</h3>
          <div className="checkbox-container">
          <ThemeProvider theme={muiTheme}>
              <FormGroup>
              <FormControlLabel control={<Checkbox checked={actionChecked} onChange={handleActionChange}/>} label="Action" color="primary"/>
              <FormControlLabel control={<Checkbox checked={adventureChecked} onChange={handleAdventureChange}/>} label="Adventure" color="secondary"/>
              <FormControlLabel control={<Checkbox checked={animatedChecked} onChange={handleAnimatedChange} />} label="Animated" color="secondary"/>
              <FormControlLabel control={<Checkbox checked={comedyChecked} onChange={handleComedyChange}/>} label="Comedy" color="secondary"/>
              </FormGroup>
            <FormGroup>
            <FormControlLabel control={<Checkbox checked={dramaChecked} onChange={handleDramaChange}/>} label="Drama" color="secondary"/>
              <FormControlLabel control={<Checkbox checked={fantasyChecked} onChange={handleFantasyChange}/>} label="Fantasy" color="secondary"/>
              <FormControlLabel control={<Checkbox checked={historicalChecked} onChange={handleHistoricalChange}/>} label="Historical" color="secondary"/>
              <FormControlLabel control={<Checkbox checked={horrorChecked} onChange={handleHorrorChange}/>} label="Horror" color="secondary"/>
            </FormGroup>
            <FormGroup>
            <FormControlLabel control={<Checkbox checked={musicalChecked} onChange={handleMusicalChange}/>} label="Musical" color="secondary"/>
            <FormControlLabel control={<Checkbox checked={noirChecked} onChange={handleNoirChange}/>} label="Noir" color="secondary"/>
            <FormControlLabel control={<Checkbox checked={romanceChecked} onChange={handleRomanceChange}/>} label="Romance" color="secondary"/>
        
            </FormGroup>
            <FormGroup>
            <FormControlLabel control={<Checkbox checked={sciencefictionChecked} onChange={handleScienceFictionChange}/>} label="Science Fiction" color="secondary"/>
            <FormControlLabel control={<Checkbox checked={thrillerChecked} onChange={handleThrillerChange}/>} label="Thriller" color="secondary"/>
            <FormControlLabel control={<Checkbox checked={westernChecked} onChange={handleWesternChange}/>} label="Western" color="secondary"/>
            </FormGroup>
            </ThemeProvider>
            <div></div>
          </div>
        </div>
        <div className="cast-container">
        <h3>Input the desired cast for the movie:</h3>
          <TextField color="secondary" className="textField" id="outlined-basic" label="Input the desired cast..." variant="outlined" value={cast} onChange={(event) => setCast(event.target.value)} />
        </div>
        <div className="button-container">
          <ColorButton variant="contained" size="Medium" onClick={submitParams}>Generate</ColorButton>
        </div>
      </div>
      <div className={`${isMovie ? 'buttons-container' : 'hidden'}`}>
          <ColorButton variant="contained" size="Medium" onClick={submitParams}>Regenerate</ColorButton>
          <ColorButton variant="contained" size="Medium" onClick={function(){setIsMovie(false)}}>Edit parameters</ColorButton>
        </div>
    </div>
  );
}

export default App;
