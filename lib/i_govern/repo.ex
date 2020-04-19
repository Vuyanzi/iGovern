defmodule IGovern.Repo do
  use Ecto.Repo,
    otp_app: :i_govern,
    adapter: Ecto.Adapters.Postgres
end
