# spring-GraphQL
spring graphql:GraphQL addresses these challenges by empowering clients to request precisely the data they need in a single query, facilitating navigation of child resources, and enabling multiple queries at once
similar to RPC (Remote Procedure Call)
Allows the client to request API for the data (only) required or asked for( using the query)
1. Describe your data
2. Ask for what you want
3. Get predictable result

! at the end of the type in schema attribute indicates that its a non-nullable type.
Any type without ! at the end can be null in the response from the server.

type Post {
    id: ID!
    title: String!
    text: String!
    category: String
    author: Author!
}

type Author {
    id: ID!
    name: String!
    thumbnail: String
    posts: [Post]!
}

# The Root Query for the application
type Query {
    recentPosts(count: Int, offset: Int): [Post]!
}

# The Root Mutation for the application
type Mutation {
    createPost(title: String!, text: String!, category: String, authorId: String!) : Post!
}

# configuration file
".graphqls" or ".gqls"
spring.graphql.schema.locations - modify the locations
default - main - resources- graphql - schema.gqls

# implementation of schema
1. Root Query Resolver-used to resolve values of top-level queries from our schema.
2. Field Resolver- used to resolve values nested within our response.
3. Mutations-used to implement mutations from our schema.

above can be implemented by writing beans within oru application and annotating them.
@Controller
public class PostController {

    private PostDao postDao;

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postDao.getRecentPosts(count, offset);
    }
}

The @SchemaMapping annotation maps the handler method to a field with same name in the schema uses datafetcher for that field.

Mutations should be used to inform the client that this will cause a change to the data being stored, defined in the controller by annotating the handler method with @MutationMapping. Return value form a mautation field is then treated exactly in the same as form of a query field.

@MutationMapping
public Post createPost(@Argument String title, @Argument String text,
  @Argument String category, @Argument String authorId) {

    Post post = new Post();
    post.setId(UUID.randomUUID().toString());
    post.setTitle(title);
    post.setText(text);
    post.setCategory(category);
    post.setAuthorId(authorId);

    postDao.savePost(post);

    return post;
}

#### unit testing graphql
# through a client
1. HttpGraphQlTester
2. WebSocketGraphQlTester
3. RSocketGraphQlTester

# without a client, to test on the server side
1. ExecutionGraphQlServiceTester
2. WebGraphQlTester

Each defines a Builder with options relevant to the transport. All builders extend from a common, base GraphQlTester Builder with option to extension

## live server running port:
WebTestClient client =
		WebTestClient.bindToServer()
				.baseUrl("http://localhost:8080/graphql")
				.build();

HttpGraphQlTester tester = HttpGraphQlTester.create(client);
==========================================================================================
String url = "http://localhost:8080/graphql";
WebSocketClient client = new ReactorNettyWebSocketClient();

WebSocketGraphQlTester tester = WebSocketGraphQlTester.builder(url, client).build();
==========================================================================================
URI url = URI.create("wss://localhost:8080/rsocket");
WebsocketClientTransport transport = WebsocketClientTransport.create(url);

RSocketGraphQlTester client = RSocketGraphQlTester.builder()
		.clientTransport(transport)
		.build();
==========================================================================================
ExecutionGraphQlService service = ...
ExecutionGraphQlServiceTester tester = ExecutionGraphQlServiceTester.create(service);
ExecutionGraphQlService service = ...
ExecutionId executionId = ExecutionId.generate();
ExecutionGraphQlServiceTester tester = ExecutionGraphQlServiceTester.builder(service)
		.configureExecutionInput((executionInput, builder) -> builder.executionId(executionId).build())
		.build();
==========================================================================================
Builder->
errorFilter- a predicate to suppress expected errors, so yu can inspect the data of the response.
documentSource- a strategy for loading the document for a request from a file on the classpath or from anywhere else.
responseTimeOut- how long to wait for request execution to complete before timing out.