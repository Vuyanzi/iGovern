defmodule IGovernWeb.Router do
  use IGovernWeb, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/api", IGovernWeb do
    pipe_through :api

    resources "/suggestions", SuggestionController, except: [:new, :edit]
  end
end
