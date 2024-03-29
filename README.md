# ArtNetSender
  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" />

## Overview
An Android app sending artnet signals to local network

## Description
### First Boot
- You have to decide which theme you use in the dialog shown below.
- You can change theme anytime after this.

  <img width="300" src="https://artnetsender-general.web.app/images/device-2021-01-22-103116.png" />

- Dark Theme(left) and Light Theme(right)

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" /> <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103230.png" />

- After deciding theme, the way to change theme is shown.

  <img width="300" src="https://artnetsender-general.web.app/images/device-2021-01-22-103150.png" />

### Setting IP Address
- First, press the "SET." button on the bottom-left.

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" />

- This is setting mode.  To back to normal mode, press the "SET." button again.
- Now, press the "ADD" button next to the "SET." button.

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103258.png" />

- This is what I call "adding mode".  To back to setting mode, press the "ADD" button again.
- You can also back to normal mode directly by pressing the "SET." button.
- You can set up IP Address to which ArtNet signal is send.
- Enter the IP Address using the keypad and press the "PLEASE" button.

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103309.png" />

- Then, the address you entered is shown.
- If you want to register more address, press the "ADD" button and follow the same procedure.
- You can check multiple address using the "BACK" and "NEXT" button.
- If you are to delete an address, display the address using "BACK" and "NEXT" button and press the "DEL" button.

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103346.png" />

### Send the ArtNet Signal
- What you have to do is only entering command and press the "PLEASE" button.

### Command example

- 255 is set to channel 1
```
1 FULL
1 AT FULL
1 AT 255
```

- 0 is set to channel 1
```
1 ZERO
1 AT ZERO
1 AT 0
```

- 123 is set to channel 1
```
1 AT 123
```

- 255 is set to channel 1, 2, 3, 4, and 5
```
1 THRU 5 FULL
1 THRU 5 AT FULL
1 THRU 5 AT 255
```

- 0 is set to channel 1, 2, 3, 4, and 5
```
1 THRU 5 ZERO
1 THRU 5 AT ZERO
1 THRU 5 AT 0
```

- 123 is set to channel 1, 2, 3, 4, and 5
```
1 THRU 5 AT 123
```

- 0 is set to channel 1, 255 is set to channel 5, and channel 2, 3, and 4 is set with the equal space between 0 and 255.
- Looks like stairs...
```
1 THRU 5 AT 0 THRU 255
```

- 255 is set to channel 1, 3, 5, 7, and 9
```
1 THRU 10 BY 2 FULL
1 THRU 10 BY 2 AT FULL
1 THRU 10 BY 2 AT 255
```

- 0 is set to channel 1, 3, 5, 7, and 9
```
1 THRU 10 BY 2 ZERO
1 THRU 10 BY 2 AT ZERO
1 THRU 10 BY 2 AT 0
```

- 123 is set to channel 1, 3, 5, 7, and 9
```
1 THRU 10 BY 2 AT 123
```

- 0 is set to channel 1, 255 is set to channel 9, and channel 3, 5, and 7 is set with the equal space between 0 and 255.
- Looks like stairs, too...
- Seeing is believing.
```
1 THRU 10 BY 2 AT 0 THRU 255
```

### Command Diagram
Made by [tabatkins/railroad-diagrams](https://github.com/tabatkins/railroad-diagrams)

  <img src="https://artnetsender-general.web.app/images/command_diagram.svg" />

## License
This software is released under the GPLv3 License, see "LICENSE".

<!--
Diagram(
  Sequence('<Channel>'),
  Choice(0,
    Choice(0,
      Choice(0,
        'FULL',
        'ZERO'),
      Sequence('AT',
        Choice(0,
          '<Value>',
          'FULL',
          'ZERO'))),
    Sequence('THRU','<Channel>',
      Optional(
        Sequence('BY', '<Interval>'),
        'skip'),
      Choice(0,
        Choice(0,
          'FULL',
          'ZERO'),
        Sequence('AT',
          Choice(0,
            Sequence('<Value>',
              Optional(
                Sequence('THRU', '<Value>'),
                'skip')),
            Choice(0,
              'FULL',
              'ZERO')))))))
-->
