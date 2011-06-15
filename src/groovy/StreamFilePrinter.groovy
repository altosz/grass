class StreamFilePrinter extends Thread {
    InputStream inputStream
    File outputFile

    StreamFilePrinter(InputStream inputStream, File outputFile) {
        this.inputStream = inputStream
        this.outputFile = outputFile
    }

    public void run() {
        new BufferedReader(new InputStreamReader(inputStream)).withReader {reader ->
            String line
            while ((line = reader.readLine()) != null) {
                outputFile.append(line + "\n")
            }
        }
    }
}