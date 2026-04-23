## PLUGIN_NAME - Documentation

## Overview
A short summary of the main functionality and purpose of the plugin.

## Features
Explain the core features and behavior of the plugin.

***

## Commands

`/PLUGIN_ALIAS help [page]`

Description: Displays a paginated and interactive list of all available plugin commands.
- Permission: `PLUGIN_LOWERCASE_NAME.command.help`
- Default: All players
- Argument(s):
  - `<page>` (Optional): The page number of the help menu you want to view in case you have multiple commands.

Example:
```
/PLUGIN_ALIAS help
```

`/PLUGIN_ALIAS reload`

Description: Reloads all plugin configurations, including `config.yml` and `messages.yml`.
- Permission: `PLUGIN_LOWERCASE_NAME.command.reload`
- Default: OP only

Example:
```
/PLUGIN_ALIAS reload
```

***

### Permissions
These are wildcard permissions that grant access to broad categories of features.

`PLUGIN_LOWERCASE_NAME.*`

Description: Grants access to all plugin features and commands.
- Default: OP

`PLUGIN_LOWERCASE_NAME.command.*`

Description: Allows the use of all plugin commands.
- Default: OP

`PLUGIN_LOWERCASE_NAME.bypass.*` (if applicable)

Description: Allows bypassing all plugin restrictions and limits.
- Default: OP

***

### Placeholders (if applicable)

`%PLUGIN_LOWERCASE_NAME_<placeholder>%`

Description: Describe what the placeholder outputs.
- Example Output: `example`