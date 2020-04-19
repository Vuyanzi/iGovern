# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

config :i_govern,
  ecto_repos: [IGovern.Repo]

# Configures the endpoint
config :i_govern, IGovernWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "2OGSFfk4wT17zQE3W/OYxS35rb8HnRq61VTh7w00aIeH+89Nx5hfDsuyvxGMZCvB",
  render_errors: [view: IGovernWeb.ErrorView, accepts: ~w(json)],
  pubsub: [name: IGovern.PubSub, adapter: Phoenix.PubSub.PG2],
  live_view: [signing_salt: "uoyMEiRg"]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
