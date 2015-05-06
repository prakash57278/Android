@echo off

::
:: Absolute path of the Termite CLI tool
::
setx TERMITE_CLI_PATH "D:\Repositories\Android\Termite\Termite-Cli" /m

::
:: Target platform; one of: mac, linux, or windows
::
setx TERMITE_PLATFORM windows /m
