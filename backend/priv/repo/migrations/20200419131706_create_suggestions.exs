defmodule IGovern.Repo.Migrations.CreateSuggestions do
  use Ecto.Migration

  def change do
    create table(:suggestions) do
      add :device, :string
      add :content, :string
      add :status, :string
      add :county, :string

      timestamps()
    end
  end
end
