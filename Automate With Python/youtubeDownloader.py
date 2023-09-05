from pytube import YouTube
import pytube.cli as pytube_cli

link = input("Video link: ")


def progress_function(stream, chunk, bytes_remaining):
        progress = round((1-bytes_remaining/youtubeObject_video.filesize)*100, 2)
        if (progress < 100.0):
            print(progress, '% done...')

        




youtubeObject = YouTube(link, on_progress_callback=progress_function)
videoTitle = input("Title of the video: ")
output_path = "C:\\Users\\16132\\Downloads"
youtubeObject_video = youtubeObject.streams.get_highest_resolution()
try:
        print(f"Downloading '{videoTitle}'...")

        # Using the pytube_cli to download the video with progress bar
        youtubeObject_video.download(filename=videoTitle + ".mp3", output_path= output_path)

        print(f"Download of '{videoTitle}' is completed successfully")

except Exception as e:
        print("An error has occurred:", e)



