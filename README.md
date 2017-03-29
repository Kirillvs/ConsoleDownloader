# Console HTTP Downloader

Simple console http downloader

## Usage

Params:
- -n number of threads (1,2,3,4....)
- -l summary speed limitation for all threads - Bps, it's allowed to use suffixes k,m (k=1024, m=1024*1024)
- -f path to file with list of links
- -o destination folder

File with list of links format:
`<link><space><destination file name>`  
examples:  
 - http://example.com/archive.zip my_archive.zip
 - http://example.com/image.jpg picture.jpg

usage example:  
java -jar ConsoleDonwloadUtility-0.1.4.jar -n 5 -l 2000k -o output_folder -f links.txt
