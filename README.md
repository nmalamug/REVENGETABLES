# EC327 Final Project - Group 4 - REVENGETABLES

<img src="RevengetablesTitle.jpg" width="50%" height="50%">

## Download Instructions
1. Create a new Android Studio project from VCS (Version Control).
2. Copy and paste the HTTPS clone link of this reposiroty int the box labeled "URL": https://agile.bu.edu/gitlab/ec327_projects/group4project.git
3. Set up the emulator, recommended settings are a pixel 6 with API 29 or later.
4. When all files are loaded, build then run the app!
5. Kick those Vegetables!!!

### Supporting material
1. [User Manual](https://docs.google.com/document/d/19Io7zJdGuWEEKzF4rUeU8P-vd8Ub-K8MtFWIdVDUoWU/edit?usp=sharing)
2. [Demo Video](https://drive.google.com/file/d/1OVGnXJZQuWPhYetlUjdvDVafIMA1yicY/view?usp=share_link)

## Summary
### Category
Animated Game

### TL;DR
Infectious vegetables have infiltrated the farm and it is your responsibility to protect the crops and the farm.

### Description
Watch out! Infected vegetables are attacking, and it is your mission is to safeguard the farm. To fend off the relentless vegetable monsters, tap and drag your farmer to strike them down. As the game progresses, these menacing creatures will grow both in quantity and speed. 
But don't lose hope! Each farmer possesses a unique and powerful special ability, hand-crafted for the task. Geraldo the Dasher can leap to any point on the screen with a single tap, vanquishing any monster in his path. Alternatively, Debbie the Demolitionist can trigger massive explosions to send those vegetables flying. After using these abilities, your character turns blue, indicating that the special attacks are inactive. Use them strategically! 
Also keep an eye on the red hearts in the upper right corner, as they represent your total number of lives. Beware! A grey heart signifies the loss of a life after a monster reaches the barn. Once all lives are depleted, the game ends. Modify the difficulty level in the settings to alter the vegetables' speed and your number of lives. Harder difficulty means higher score bonuses, but also more enemies! Embrace the challenge, have a blast, and defend your barn from the wicked vegetable invasion!

## Authors
### Group members
HarmanSingh - hsingh2@bu.edu  
NicolasMalamug - nmalamug@bu.edu  
MaikoLum - mlum@bu.edu  
JoscelynnPalen - joscie@bu.edu  
BennettTaylor - betaylor@bu.edu

### Roles
###### BackEnd 
- [ ] Harman Singh    - 40%  
- [ ] Nicolas Malamug - 40%
- [ ] Joscelynn Palen - 20%

###### Frontend
- [ ] Joscelynn Palen - 20%  
- [ ] Maiko Lum       - 60% 
- [ ] Bennett Taylor  - 20%  

###### Tester
- [ ] Joscelynn Palen - 30% 
- [ ] Maiko Lum       - 20%
- [ ] Bennett Taylor  - 30%
- [ ] Nicolas Malamug - 10%
- [ ] Harman Singh    - 10%
###### Documentation 
- [ ] Bennett Taylor  - 50%
- [ ] Maiko Lum       - 20% 
- [ ] Joscelynn Palen - 30% 
###### Project Lead
- [ ] Harman Singh    - 50%
- [ ] Nicolas Malamug - 50% 

---
## Accomplishments

### Minimum requirements
##### Fully completed

"Moving graphical pieces."  
"The ability of the user to control some of the moving pieces through input."  
"Include a score that is changed based on the user satisfying requirements."  
"Have a simple-to-use, graphical user interface."  
"Provide clear documentation of the rules of the game."  
"Not require any Internet or network connectivity."  
"Not violate the copyright of Tetris or any other game."  

##### Partially Completed
We believe that all of the minimum requirements have been fullfilled in their entirety.


### Possible features
##### Fully completed
"Provide a high-score list that persists when the app is closed and then reopened. [10%]"  
"Allow the user to tweak the rules of the game being played. [10%]"  

##### Partially Completed
"Do all calculations in C++ and connect them to the app through Android's Native Development Kit. [20%]"  
* 15/20 Completed
* Although a bulk majority of backend and heavy calculations were done in C++, some of the calculations, including management of enemies, were still done in java. 

"Add sound effects for specific activities in the game. [10%]" 
* 8/10 Completed
* We have sound effects in the game for the major aspects of the game. Improving the game experience may be accomplished by adding distinct, creature-specific audio for every monster-farmer interaction that occurs.


---
## Execution

### Project source
No External Libraries were utilized. We primarily imported local Android Studio libraries
### Installation
1. Open up the terminal on your machine, type "androidstudio", and press enter.
2. Create a new Android Studio project from VCS (Version Control).
3. Copy and paste the HTTPS clone link of this repository in the box labeled "URL": https://agile.bu.edu/gitlab/ec327_projects/group4project.git
4. Set up the emulator, recommended settings are a pixel 6 with API 29 or later.
5. When all files are loaded, build and then run the app!
6. Kick those Vegetables!!!

### Usage
1. To change playing features (Player type, difficulty, sound) click on the "Settings" button and select your desired options.
2. Click on the "User Manual" button to view detailed instructions on how to play.
3. Click on the "High Scores" button to view the top ten scores.
4. Click on "Play" to beat those veggies!

---
## Miscellaneous

### Extra features
1. Visual effects when explosion is used 
2. All illustrations are original
3. Different monster types that follow a different movement pattern including: hopping (sinusoidal movement), diagonal movement, straight movement
4. We haven't yet launched our app on the Google Play Store, but we're working on developing an enhanced version this summer to ensure we deliver the best possible user experience for our customers.


### Challenges
1. Adjust to the large scale of the project - created a system to keep GIT repo organized
2. Learning the tools and levels of abstraction in Android Studio
3. Dealing with Java code and its minor differences from C++ code
4. Dealing with android studio's security protocol when writing to files and extracting files
5. Using GameFragments rather than Activities posed an issue when implementing sound into our game
6. Initially, we aimed to display individual monster hit points, but due to a disconnect between the front and backend, the simplified monster class in the backend did not account for the individuality of each monster, preventing the front end from utilizing the desired feature.

### Release
We have reached a consensus where we would all like for the project to be displayed publicly. 
### 
