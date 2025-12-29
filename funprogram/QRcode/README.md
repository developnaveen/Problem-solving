# QR generator 
# use zxing
- image size is 300 x 300

```bash
BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
``` 
- this line will say that analysis the text and divide as the module 
- fit in to the 300 * 300 pixel
- save into jpeg image 

# scanning 
- used Buffered Image 
- converted into bitmap
- read the data 
