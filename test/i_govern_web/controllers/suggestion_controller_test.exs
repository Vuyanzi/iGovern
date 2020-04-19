defmodule IGovernWeb.SuggestionControllerTest do
  use IGovernWeb.ConnCase

  alias IGovern.Suggestions
  alias IGovern.Suggestions.Suggestion

  @create_attrs %{
    device: "some device",
    status: "some status",
    suggestion: "some suggestion"
  }
  @update_attrs %{
    device: "some updated device",
    status: "some updated status",
    suggestion: "some updated suggestion"
  }
  @invalid_attrs %{device: nil, status: nil, suggestion: nil}

  def fixture(:suggestion) do
    {:ok, suggestion} = Suggestions.create_suggestion(@create_attrs)
    suggestion
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all suggestions", %{conn: conn} do
      conn = get(conn, Routes.suggestion_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create suggestion" do
    test "renders suggestion when data is valid", %{conn: conn} do
      conn = post(conn, Routes.suggestion_path(conn, :create), suggestion: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.suggestion_path(conn, :show, id))

      assert %{
               "id" => id,
               "device" => "some device",
               "status" => "some status",
               "suggestion" => "some suggestion"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.suggestion_path(conn, :create), suggestion: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update suggestion" do
    setup [:create_suggestion]

    test "renders suggestion when data is valid", %{
      conn: conn,
      suggestion: %Suggestion{id: id} = suggestion
    } do
      conn =
        put(conn, Routes.suggestion_path(conn, :update, suggestion), suggestion: @update_attrs)

      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.suggestion_path(conn, :show, id))

      assert %{
               "id" => id,
               "device" => "some updated device",
               "status" => "some updated status",
               "suggestion" => "some updated suggestion"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, suggestion: suggestion} do
      conn =
        put(conn, Routes.suggestion_path(conn, :update, suggestion), suggestion: @invalid_attrs)

      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete suggestion" do
    setup [:create_suggestion]

    test "deletes chosen suggestion", %{conn: conn, suggestion: suggestion} do
      conn = delete(conn, Routes.suggestion_path(conn, :delete, suggestion))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.suggestion_path(conn, :show, suggestion))
      end
    end
  end

  defp create_suggestion(_) do
    suggestion = fixture(:suggestion)
    {:ok, suggestion: suggestion}
  end
end
