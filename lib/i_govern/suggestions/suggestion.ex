defmodule IGovern.Suggestions.Suggestion do
  use Ecto.Schema
  import Ecto.Changeset
  import Ecto.Query

  schema "suggestions" do
    field :device, :string
    field :status, :string
    field :content, :string
    field :county, :string
    field :title, :string
    field :type, :string

    timestamps()
  end

  @doc false
  def changeset(suggestion, attrs) do
    suggestion
    |> cast(attrs, [:device, :content, :status, :county, :title, :type])
    |> validate_required([:device, :content, :county])
  end

  def filter(params) do
    __MODULE__
    |> where(^filter_where(params))
  end

  defp filter_where(params) do
    Enum.reduce(params, dynamic(true), fn
      {"device", value}, dynamic ->
        dynamic([s], ^dynamic and s.device == ^value)

      {"status", value}, dynamic ->
        dynamic([s], ^dynamic and s.status == ^value)

      {"county", value}, dynamic ->
        dynamic([s], ^dynamic and s.county == ^value)

      {"type", value}, dynamic ->
        dynamic([s], ^dynamic and s.type == ^value)

      {"title", value}, dynamic ->
        dynamic([s], ^dynamic and ilike(s.title, ^"%#{value}%"))

      {_, _}, dynamic ->
        # Not a where parameter
        dynamic
    end)
  end
end
