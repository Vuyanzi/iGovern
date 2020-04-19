use Mix.Config

# Configure your database
config :i_govern, IGovern.Repo,
  username: "postgres",
  password: "postgres",
  database: "i_govern_test",
  hostname: "localhost",
  pool: Ecto.Adapters.SQL.Sandbox

# We don't run a server during test. If one is required,
# you can enable the server option below.
config :i_govern, IGovernWeb.Endpoint,
  http: [port: 4002],
  server: false

# Print only warnings and errors during test
config :logger, level: :warn
