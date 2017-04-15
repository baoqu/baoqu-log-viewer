# baoqu-log-viewer

Generate a HTML static page with the logs from a Baoqu event

## Usage

```
$ java -jar baoqu-log-viewer.jar --help
Options:
  -i, --input INPUT_FILE                                The sqlite database to read from
  -o, --output OUTPUT_FILE  Defaults to baoqu_log.html  The HTML file to write to
  -h, --help                                            Shows this help
```

## Generate the documentation

To generate the [marginalia](https://github.com/gdeer81/marginalia) powered documentation, just run `lein doc`.
