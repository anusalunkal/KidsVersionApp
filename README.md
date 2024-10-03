# Interactive Storytelling App

This repository contains the code and resources for the Interactive Storytelling App designed for Android devices. Follow the steps below to set up and run the app.

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Android Studio**
   - Download and install from [Android Studio Official Site](https://developer.android.com/studio).
   
2. **XAMPP**
   - Download and install XAMPP from [this link](https://sourceforge.net/projects/xampp/files/XAMPP%20Windows/1.8.3/xampp-win32-1.8.3-4-VC11-installer.exe/download).

3. **Clone the Git Repository**
   - Clone this repository to your local machine using the following command:
     ```bash
     git clone https://github.com/your-username/your-repository-name.git
     ```

## Steps to Run the App

Follow these steps to run the Interactive Storytelling App on your Android device:

1. **Open the Project**
   - Launch Android Studio and open the `StoryTellingApp` project from the `Android` folder.

2. **Connect Your Device**
   - Connect your Android phone to your laptop/PC using a USB cable.

3. **Enable Developer Options**
   - On your phone, navigate to **Settings** > **About Phone** > tap **Build Number** 7 times to enable Developer Options.
   - Go back to **Settings** > **Developer Options** and turn it on.

4. **Run the App**
   - Once your phone is connected and recognized by Android Studio (the device name should display), click on the **Run** button in Android Studio. The app will be installed on your phone.

5. **Set Up PHP Server**
   - Copy the `kids_stories` folder from the `php` folder in this GitHub repository.
   - Paste the `kids_stories` folder inside the `htdocs` directory of your XAMPP installation on your laptop/PC.

6. **Connect Devices to the Same Network**
   - Ensure that both your laptop/PC and your phone are connected to the same Wi-Fi network.

7. **Find Your IP Address**
   - Open the Command Prompt on your laptop/PC and type the following command:
     ```bash
     ipconfig
     ```
   - Locate and note the **IPv4 Address**.

8. **Connect the App**
   - Open the Interactive Storytelling App on your phone and enter the noted IP address to connect.


