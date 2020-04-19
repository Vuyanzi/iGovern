# IGovern

## Prerequisites

  * You must have [Elixir](https://elixir-lang.org/install.html) installed and Postgres running

To start the server:

  * Install dependencies with `mix deps.get`
  * Create and migrate your database with `mix ecto.setup`
  * Start IGovern endpoint with `mix phx.server`

Now you can visit [`localhost:4000`](http://localhost:4000) from your browser.

## API GUIDE

  * ### Creating a suggestion
    `POST /api/suggestions`
    #### Body
    `{suggestion: {content: " ", device: " ", county: " "}}`
  * ### Listing suggestions
    `GET /api/suggestions`
    #### Query params
    * `device`
    * `status`
    * `county`

